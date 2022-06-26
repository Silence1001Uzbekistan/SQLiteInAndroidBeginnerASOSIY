package uz.silence.sqliteinandroidbeginnercontactapp.CLASS

class Contact {

    var id:Int? = null
    var name:String? = null
    var phoneNumber:String? = null



    constructor()
    constructor(id: Int?, name: String?, phoneNumber: String?) {
        this.id = id
        this.name = name
        this.phoneNumber = phoneNumber
    }

    constructor(name: String?, phoneNumber: String?) {
        this.name = name
        this.phoneNumber = phoneNumber
    }
}