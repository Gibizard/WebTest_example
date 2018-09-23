package com.example

import org.jaudiotagger.audio.AudioFileIO
import org.jaudiotagger.audio.mp3.MP3File
import org.jaudiotagger.tag.FieldKey
import java.io.File

class SongList {
    val path: File = File("songs")
    val files: Array<File>? = path.listFiles()
    var songs: ArrayList<Song> = ArrayList()

    fun makeList():Boolean {
        songs.removeAll(songs)
        if (files != null) {
            for (item in files) {
                val f: MP3File = AudioFileIO.read(item) as MP3File
                val song = Song(f.iD3v1Tag.getFirst(FieldKey.TITLE).toLowerCase(),f.iD3v1Tag.getFirst(FieldKey.ARTIST).toLowerCase(),f.iD3v1Tag.getFirst(FieldKey.ALBUM).toLowerCase(), f.mP3AudioHeader.trackLengthAsString)
                songs.add(song)
            }
            return true
        } else return false
    }

    fun print(){
        println("======")
        for (item in songs) println(item)
        if (songs.isEmpty()) println("No Mp3 in \"song\" directory")
        println("======")
    }
}