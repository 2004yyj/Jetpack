# RxJava 시작하기

### 리액티브 프로그래밍이란?

명령형 프로그래밍 방식은 변화가 있든 없든 기능을 무조건 수행하도록 되어 있었는데, 리액티브 프로그래밍은 프로그램 내에서 이벤트가 발생하면, 이에 반응하여 처리하는 것이다. 

Push-Pull 전략으로도 설명할 수 있는데,

| 명령형 프로그래밍(Pull 방식)                                 |
| ------------------------------------------------------------ |
| 소비자가 물품을 구매하면 쌓아 둔 것 하나를 배송한다. (소비자가 있든 없든 실행) |
| 재고가 남을 수도 있기 때문에 비효율적이다.                   |

| 리액티브 프로그래밍(Push 방식)                               |
| ------------------------------------------------------------ |
| 소비자가 물품을 구매하면 즉시 제작해서 배송한다. (소비자 발생 시 실행) |
| 바로 물건을 제작해서 보내기 때문에 재고가 남지 않아 효율적이다. |

------

### ReactiveX의 구성

1. **발행 클래스**

|       클래스       |                             용도                             |
| :----------------: | :----------------------------------------------------------: |
| Observable 클래스  |   데이터 흐름에 맞게 옵저버에게 알림을 보내는 클래스이다.    |
|    Maybe 클래스    | Observable의 특정 연산자를 사용하여 데이터 발행 없이 완료할 수 있는 클래스이다. |
|  Flowable 클래스   | Flowable 클래스는 Observable 클래스와 기능이 기본적으로 동일하나, <br />발행될 데이터가 방대(10,000개 이상)할 경우, <br />DB의 쿼리 결과를 가져오는 경우,<br /> 네트워크 입출력을 실행하는 경우에 사용하는 클래스이다. |
|   Single 클래스    | 항상 `onSuccess()` 또는 `onError()`, 하나의 성공/실패 알림만 전달하는 클래스이다. |
| Completable 클래스 | 다른 클래스들은 반환값이 무조건 있어야 하지만, <br />반환값 없이 끝냈음을 알리는 `onCompleted()` 와 오류를 알리는 `onError(throwable)` 만을 가진다. |



2. **연산자(Operators)** : 발행된 데이터를 생성, 변환, 필터링 등등 처리하는 연산자들이 있다. 연산자들은 [이 곳](http://reactivex.io/documentation/ko/operators.html)에서 확인해볼 수 있다.



3. **스케쥴러(Scheduler)** : 발행된 데이터를 연산하는 스레드, 연산 이후 최종적으로 구독하는 스레드를 지정하는 것이다.

|     스케쥴러(자바 기준)     |                             용도                             |
| :-------------------------: | :----------------------------------------------------------: |
| `Schedulers.computation()`  | 연산 중심적인 작업을 위해 사용된다. I/O를 용도로 사용하면 안 된다. |
| `Schedulers.from(executor)` |            명시한 executor를 스케쥴러로 사용한다.            |
|  `Schedulers.immediate()`   | 현재 스레드를 스케쥴러로 사용하여 즉시 실행할 작업을 스케쥴링 한다. |
|      `Schedulers.io()`      | 비동기 연산과 같은 I/O 작업을 처리하며, 스케쥴러는 필요한 만큼 증가한다. |
|  `Schedulers.newThread()`   |                      새로운 스레드 생성                      |
|  `Schedulers.trampoline()`  |   대기 큐를 처리 후 현재 스레드에서 실행 될 작업 큐를 생성   |

------

### Room과 RxJava를 함께 사용해보자

1. **시작하기 앞서**

EditText 입력 후 추가 버튼을 누르면 TextView에 [{title = 1}, {title = 2}] 와 같이 TextView에 출력되도록 할 것이다.

2. **Todo 데이터 클래스 작성**

```kotlin
@Entity
data class Todo(
    var title : String
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}
```



3. **TodoDao Interface 작성**

```kotlin
@Dao
interface TodoDao {
    @Query("SELECT * FROM Todo")
    fun getAll(): Observable<List<Todo>> // List<Todo>라는 리스트 속에 객체가 변할 때 마다 알림을 보낼 수 있도록 한다.

    @Insert
    fun insert(todo : Todo) : Completable // 하나의 객체값만을 전달하기 때문에 Completable을 사용하였다.

    @Update
    fun update(todo : Todo) : Completable

    @Delete
    fun delete(todo : Todo) : Completable
}
```



4. **DB 추상 클래스 작성**

```kotlin
@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao() : TodoDao
}

```



5. **MainActivity 클래스 작성 - Room 데이터베이스 생성**

```kotlin
val db = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java,
        "todo-db-kt-RxJava"
).build()
```

Room의 databaseBuilder에 applicationContext와 

위에 생성한 AppDatabase 인스턴스를 가져와 "todo-db-kt-RxJava" 라는 로컬 파일에 DB를 저장한다.



6. **MainActivity 클래스 작성 - 버튼**

```kotlin
button.setOnClickListener {
    db.todoDao().insert(Todo(editText.text.toString()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
}
```

버튼 클릭 시 데이터베이스에 EditText의 내용을 텍스트로 변환하여 Todo 객체에 넣는다.

구독을 할 때는 RxJava의 스케쥴러인 ``Schedulers.io()``를 사용하고,

데이터베이스의 변화를 관찰할 때, RxAndroid의 스케쥴러인 ``AndroidSchedulers.mainThread()``를 사용한다.



7. **MainActivity 클래스 작성 - 텍스트 뷰에 데이터베이스 값 출력**

```kotlin
db.todoDao().getAll()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { todo ->
            textView.text = todo.toString()
        }
```

데이터베이스의 변화가 있을 때 TextView 역시 변화해야 하는데,

이 또한 구독 시 ``Scheduler.io()``를 사용하고,

DB 변화 관측은  ``AndroidSchedulers.mainThread()``를 사용한다.

구독 할 때는 ``Schedulers.io()``스케쥴러에서 반환값을 텍스트로 바꿔 텍스트뷰에 텍스트를 set해 준다.

하지만 위 코드에는 **심각한 결함**이 있다. 

구독자가 텍스트뷰를 참조하고 있어 비정상적으로 종료되었을 때, 

**텍스트뷰는 가비지 컬렉션(GC)의 대상이 아니기 때문에 메모리 누수가 발생**하기 때문이다.




8. **MainActivity 클래스 작성 - 메모리 누수 방지**

```kotlin
    private val compositeDisposable = CompositeDisposable()
```

CompositeDisposable 객체를 사용하면, 생성된 모든 Observable 객체를 안드로이드 라이프사이클에 맞춰 소멸시킬 수 있다.



- onCreate() 작성

```kotlin
compositeDisposable.add(
db.todoDao().getAll()
        .subscribeOn(Scheduler.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { todo ->
            textView.text = todo.toString()
        }
)
```

CompositeDisposable 클래스의 메소드 중 ``add()``, ``addAll()``을 생성 시 사용할 수 있다.

|    메소드    |                        용도                         |
| :----------: | :-------------------------------------------------: |
|  ``add()``   |     한 가지의 객체만을 소멸시키고자 할 때 사용      |
| ``addAll()`` | 여러 가지의 객체를 한꺼번에 소멸시키고자 할 때 사용 |



- onDestroy() 작성

```kotlin
override fun onDestroy() {
    super.onDestroy()
    compositeDisposable.clear()
}
```

이후 액티비티가 소멸되기 직전에 ``add()``또는 ``addAll()``한 객체들을 소멸시키기 위해

CompositeDisposable의 메소드 중 ``clear()``또는 ``dispose()`` 라는 것을 사용하게 된다.

이 둘은 객체를 해제시키는 기능은 같으나, 차이점이 있다.

|    메소드     |                            차이점                            |
| :-----------: | :----------------------------------------------------------: |
|  ``clear()``  |         소멸 이후 새로운 Disposable을 받을 수 있음.          |
| ``dispose()`` | 소멸 이후 ``isDisposed()``함수를 true로 설정하여 새로운 Disposable을 받을 수 없음. |





