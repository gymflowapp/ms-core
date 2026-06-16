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
        public static final String GYMS = "/gyms";
        public static final String GYM_BY_ID = "/{gymId}";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Gym {

        public static final String NOT_FOUND = "Gimnasio no encontrado";
        public static final String NAME_REQUIRED = "El nombre del gimnasio es obligatorio";
        public static final String NAME_MAX_LENGTH = "El nombre no puede superar 255 caracteres";
        public static final String PLAN_REQUIRED = "El plan es obligatorio";
        public static final String PHONE_MAX_LENGTH = "El teléfono no puede superar 20 caracteres";
        public static final String ADDRESS_MAX_LENGTH = "La dirección no puede superar 500 caracteres";
        public static final String EMAIL_INVALID = "El correo electrónico no es válido";
        public static final String EMAIL_MAX_LENGTH = "El correo electrónico no puede superar 255 caracteres";
        public static final String LOGO_URL_MAX_LENGTH = "El logo URL no puede superar 500 caracteres";
        public static final String BRANDING_COLORS_MAX_LENGTH = "El color no puede superar 9 caracteres";
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
