package com.example.practico3.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practico3.R
import com.example.practico3.model.Person

class PeopleViewModel : ViewModel() {
    private val _peopleList = MutableLiveData<List<Person>>()
    val peopleList: LiveData<List<Person>> get() = _peopleList

    private val _likedPeople = MutableLiveData<List<Person>>()
    val likedPeople: LiveData<List<Person>> get() = _likedPeople

    private val currentPeople = mutableListOf(
        Person("Barak", listOf(R.drawable.img1, R.drawable.img2, R.drawable.img3)),
        Person("Vladimir", listOf(R.drawable.img4, R.drawable.img5, R.drawable.img6)),
        Person("Kim", listOf(R.drawable.img7, R.drawable.img8, R.drawable.img9)),
        Person("Xin", listOf(R.drawable.img10, R.drawable.img11, R.drawable.img12)),
        Person("Boris", listOf(R.drawable.img13, R.drawable.img14, R.drawable.img15)),
        Person("Charles", listOf(R.drawable.img16, R.drawable.img17, R.drawable.img18)),
        Person("Joe", listOf(R.drawable.img19, R.drawable.img1, R.drawable.img1)),
        Person("Laura", listOf(R.drawable.img1, R.drawable.img1, R.drawable.img1)),
        Person("Diego", listOf(R.drawable.img1, R.drawable.img1, R.drawable.img1)),
        Person("Elena", listOf(R.drawable.img1, R.drawable.img1, R.drawable.img1)),
        Person("Pedro", listOf(R.drawable.img1, R.drawable.img1, R.drawable.img1)),
        Person("Lucia", listOf(R.drawable.img1, R.drawable.img1, R.drawable.img1)),
        Person("Andres", listOf(R.drawable.img1, R.drawable.img1, R.drawable.img1)),
        Person("Paula", listOf(R.drawable.img1, R.drawable.img1, R.drawable.img1)),
        Person("Fernando", listOf(R.drawable.img1, R.drawable.img1, R.drawable.img1))
    )

    init {
        _peopleList.value = currentPeople
        _likedPeople.value = listOf()
    }

    fun likePerson(person: Person) {
        currentPeople.remove(person)
        _peopleList.value = currentPeople
        _likedPeople.value = _likedPeople.value?.toMutableList()?.apply { add(person) }
    }

    fun dislikePerson(person: Person) {
        currentPeople.remove(person)
        _peopleList.value = currentPeople
    }
}
