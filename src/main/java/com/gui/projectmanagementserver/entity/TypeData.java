package com.gui.projectmanagementserver.entity;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class TypeData {
    // Type ?
    public static final Type LOGIN_OBJECT = new TypeToken<LoginObject>() {}.getType();

    public static final Type REGESTER_OBJECT = new TypeToken<RegesterObject>() {}.getType();

    public static final Type STRING = new TypeToken<String>() {}.getType();

    public static final Type CREATE_PROJECT = new TypeToken<CreateProjectObject>() {}.getType();
    public static final Type UPLOAD_PRODUCT = new TypeToken<CreateProductObject>() {}.getType();
    public static final Type TASK = new TypeToken<TaskObject>() {}.getType();
    public static final Type CREATE_TASK = new TypeToken<CreateTaskProject>() {}.getType();

    public static final Type MESSAGE_SEND = new TypeToken<MessageSend>() {}.getType();
    // public static final Type CREATE_PROJECT = new TypeToken<CreateProjectObject>() {}.getType();
    public static final Type REQUESTS = new TypeToken<List<RequestAddContact>>() {}.getType();
    public static final Type CREATE_FEEDBACK = new TypeToken<CreateFeedBack>() {}.getType();
    public static final Type BASIC_INFORMATION = new TypeToken<BasicInformation>() {}.getType();
    public static final Type BOOLEAN = new TypeToken<Boolean>() {}.getType();

}
