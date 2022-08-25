package com.group3.wineshop.utilities;

import com.group3.wineshop.entities.Wine;

import java.util.Comparator;

public class RatingToPriceRatioComparator implements Comparator<Wine>
    {
        public int compare(Wine wine1, Wine wine2) {
            Double wine1Rating = wine1.getRating();
            Double wine2Rating = wine2.getRating();
            Double wine1Price = wine1.getPrice();
            Double wine2Price = wine2.getPrice();

            long priceLCM = getWinePricesLCM(wine1Price, wine2Price);
            Double fullRatio1 = wine1Rating *(priceLCM / wine1Price);
            Double fullRatio2 = wine2Rating *(priceLCM / wine2Price);

            return fullRatio1.compareTo(fullRatio2);
        }

        private long getWinePricesLCM(Double price1, Double price2) {
            long priceAbsNumber1 = Math.abs(Math.round(price1));
            long priceAbsNumber2 = Math.abs(Math.round(price2));
            long priceAbsHigherNumber = Math.max(priceAbsNumber1, priceAbsNumber2);
            long priceAbsLowerNumber = Math.min(priceAbsNumber1, priceAbsNumber2);
            long priceLCM = priceAbsHigherNumber;
            while (priceLCM % priceAbsLowerNumber != 0) {
                priceLCM += priceAbsHigherNumber;
            }

            return priceLCM;
        }
    }