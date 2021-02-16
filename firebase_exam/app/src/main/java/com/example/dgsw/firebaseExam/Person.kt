package com.example.dgsw.firebaseExam

data class Person(var name : String, var phoneNum : String) {
    constructor() : this("", "") // Firebase FireStore 의 클래스로 사용하기 위해서는 빈 생성자가 필요하다.
}