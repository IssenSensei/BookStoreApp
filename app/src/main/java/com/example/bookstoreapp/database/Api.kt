package com.example.bookstoreapp.database

object Api {

    private val ROOT_URL = "http://192.168.88.38/BookstoreApi/api/Api.php?apicall="

    val URL_GET_USERS = ROOT_URL + "getUsers"
    val URL_GET_BOOKS = ROOT_URL + "getBooks"
    val URL_GET_NEWS = ROOT_URL + "getNews"
    val URL_GET_USER_QUOTES = ROOT_URL + "getUserQuotes&id=3"
    val URL_GET_BOOK_QUOTES = ROOT_URL + "getBookQuotes&id=3"


    val URL_GET_USER_ID = ROOT_URL + "getUserId"
    val URL_GET_USER = ROOT_URL + "getUser&id=3"
    val URL_CREATE_USER = ROOT_URL + "createUser"
    val URL_DELETE_USER = ROOT_URL + "deleteUser&id="
    val URL_UPDATE_USER = ROOT_URL + "updateUser"
    val URL_ADD_USER_QUOTE = ROOT_URL + "addUserQuote"
    val URL_CHANGE_PASSWORD = ROOT_URL + "changePassword"


    val CODE_GET_REQUEST = 1024
    val CODE_POST_REQUEST = 1025
}