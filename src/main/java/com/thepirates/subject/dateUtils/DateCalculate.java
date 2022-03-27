package com.thepirates.subject.dateUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DateCalculate {

    public static List<String> getDeliveryDate(boolean closeTime, String slowOrFastDelivery) {
        List<String> selectDeliveryCollect = new ArrayList<>();
        LocalDate nowDay = LocalDateTime.now().toLocalDate();

        // 익일배송, 당일 배송
        int deliveryTodayOrTomorrow = 0;
        // 마감시간 지났는지 +1 안지났는지 + 0.
        int closingTimeUnderOrExcess = closeTime ? 0 : 1;
        //익일 배송 +1 , 당일 배송 +0
        if (slowOrFastDelivery.equals("regular"))
            deliveryTodayOrTomorrow = 1;

        nowDay = weekCheck(nowDay); //주말이 껴있을 때 일요일은 +1 토요일은 +2
        nowDay = LocalDate.from(nowDay).plusDays(deliveryTodayOrTomorrow);
        nowDay = LocalDate.from(nowDay).plusDays(closingTimeUnderOrExcess);

        for (int i = 0 ; i < 5 ; i ++) {
            selectDeliveryCollect.add(nowDay.plusDays(i).format(DateTimeFormatter.ofPattern("MM월 dd일(E요일)")));
        }
        return selectDeliveryCollect;
    }

    public static LocalDate weekCheck(LocalDate nowDay) {
        if (nowDay.getDayOfWeek().toString().equals("sunday"))
            nowDay = LocalDate.from(nowDay).plusDays(1);
        else if(nowDay.getDayOfWeek().toString().equals("saturday"))
            nowDay = LocalDate.from(nowDay).plusDays(2);

        return nowDay;
    }
}
