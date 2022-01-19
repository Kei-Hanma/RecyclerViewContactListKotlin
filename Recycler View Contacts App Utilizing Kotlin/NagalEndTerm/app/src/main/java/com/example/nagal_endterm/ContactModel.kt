package com.example.nagal_endterm

class ContactModel {
    var img = 0
    var name: String
    var number: String

    constructor(img: Int, name: String, number: String) {
        this.name = name
        this.number = number
        this.img = img
    }

    constructor(name: String, number: String) {
        this.name = name
        this.number = number
    }
}