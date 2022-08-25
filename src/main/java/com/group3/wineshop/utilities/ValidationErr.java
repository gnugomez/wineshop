package com.group3.wineshop.utilities;

import org.apache.logging.log4j.util.Strings;

import java.util.Calendar;

public class ValidationErr {
    public final static String acidityInterval = "Value must be in the interval [1, 5]";
    public final static String bodyInterval = "Value must be in the interval [1, 5]";
    public final static String yearIntervalErr = "Value must be in the interval [1900, now]";
    public final static Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
    public final static String negativeErr = "Cannot be a negative value";
    public final static String ratingIntervalErr = "Value must be in the interval [0, 5]";
}
