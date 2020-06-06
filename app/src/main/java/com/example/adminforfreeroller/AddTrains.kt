package com.example.adminforfreeroller

class AddTrains {
    var id = ""
    var name = ""
    var link_picture = ""
    var complexity = ""
    var description = ""
    var link_video = ""

    constructor()

    constructor(id: String, name: String, link_picture: String, complexity: String, description: String, link_video: String) {
        this.id = id
        this.name = name
        this.link_picture = link_picture
        this.complexity = complexity
        this.description = description
        this.link_video = link_video
    }
}