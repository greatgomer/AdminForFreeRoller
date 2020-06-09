package com.svdgroup.adminforinline

class AddData {
    var id = ""
    var lat = ""
    var lon = ""
    var name = ""
    var options = ""

    constructor()

    constructor(id: String, lat: String, lon: String, name: String, options: String) {
        this.id = id
        this.lat = lat
        this.lon = lon
        this.name = name
        this.options = options
    }
}