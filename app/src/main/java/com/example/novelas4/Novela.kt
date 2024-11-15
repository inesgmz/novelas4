package com.example.novelas4

import android.os.Parcel
import android.os.Parcelable

data class Novela(
    val titulo: String = "",
    val autor: String = "",
    val año: Int = 0,
    val sinopsis: String = "",
    var esFavorita: Boolean = false,
    val resenas: MutableList<String> = mutableListOf() // Add this line
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readByte() != 0.toByte(),
        parcel.createStringArrayList() ?: mutableListOf() // Add this line
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(titulo)
        parcel.writeString(autor)
        parcel.writeInt(año)
        parcel.writeString(sinopsis)
        parcel.writeByte(if (esFavorita) 1 else 0)
        parcel.writeStringList(resenas) // Add this line
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Novela> {
        override fun createFromParcel(parcel: Parcel): Novela {
            return Novela(parcel)
        }

        override fun newArray(size: Int): Array<Novela?> {
            return arrayOfNulls(size)
        }
    }
}