package com.buyrak.datamanagmentsystem.utils

class User{

    var userId: String? = null
    var email: String? = null
    var fullName: String? = null
    var phone: String? = null
    var profilePhoto: String? = null
    var role: String? = null

    constructor(userId: String, email: String, fullName: String, phone: String, profilePhoto: String, role: String){
        this.userId = userId
        this.email = email
        this.fullName = fullName
        this.phone = phone
        this.profilePhoto = profilePhoto
        this.role = role
    }

    constructor()


}