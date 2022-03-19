package com.perspective.restwebservices;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class MyTestPrac {
    Calculator underTest = new Calculator();
    @Test
    void itShouldAddTwoNumbers(){
      //given
        int num1 = 14;
        int num2 = 20;
        //when
       int result = underTest.sum(num1,num2);
       //then
       assertThat(result).isEqualTo(34);
    }

    class Calculator {


        int sum(int num1,int num2){
            return num1+num2;
        }


    }
}
