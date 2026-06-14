package com.gymbro.core.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AppConstants {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Router {

        public static final String API_V1 = "/api/v1";
        public static final String CLIENTS = "/clients";
        public static final String CLIENT_BY_ID = "/{clientId}";
        public static final String GYM_TOKEN = "X-Gym-Token";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Client {

        public static final String INVALID_GYM_TOKEN = "El token del gimnasio no es válido";
        public static final String GYM_NOT_FOUND = "Gimnasio no encontrado";
        public static final String CLIENT_NOT_FOUND = "Cliente no encontrado";
        public static final String DOCUMENT_TYPE_NOT_FOUND = "Tipo de documento no encontrado";
        public static final String GENDER_NOT_FOUND = "Género no encontrado";
        public static final String DOCUMENT_ALREADY_EXISTS =
                "Ya existe un cliente con ese documento en el gimnasio";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Validation {

        public static final String INVALID_REQUEST = "La solicitud contiene datos inválidos";
    }
}
