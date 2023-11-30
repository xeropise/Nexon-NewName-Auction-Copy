package com.auction.common.constant;

public class UserEndPointPath {
    public static final String LOGIN = "/api/login";
    public static final String REGISTER = "/api/users";
    public static final String SEARCH = "/api/users/{userId}";

    public static final String ADD_ROLE = "/api/users/{userId}/roles";
    public static final String REMOVE_ROLE = "/api/users/{userId}/roles";
}
