package org.example.backend.util;

import java.util.UUID;

public class AppUtil {
    public static String generateCustomerId() {return "C-"+ UUID.randomUUID();}
    public static String generateItemId() {return "I-"+ UUID.randomUUID();}
    public static String generateOrderId() {return "O-"+ UUID.randomUUID();}
}
