package com.dkass.memorygame


class Level {
    val title = String
    val fails = Int
    val pairs = mutableListOf<String>()
}

fun levelsFromXmlString(xml: String): MutableList<Level> {
    val list = mutableListOf<Level>()

//    if (xml == "")
//        throw NullPointerException()

    return list
}