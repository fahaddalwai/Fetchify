package com.example.fetchify.feature.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.fetchify.feature.data.util.JsonParser
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun fromListInfoJson(json: String): List<ListInfo> {
        return jsonParser.fromJson<ArrayList<ListInfo>>(
            json,
            object : TypeToken<ArrayList<ListInfo>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toListInfoJson(listInfo: List<ListInfo>): String {
        return jsonParser.toJson(
            listInfo,
            object : TypeToken<ArrayList<ListInfo>>() {}.type
        ) ?: "[]"
    }
}