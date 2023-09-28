function convertToRoman(num) {
    let number = num;
    let romanNumeral = "";
  
    for (let i = 0; i < num; i++){
      if (number == 0){
        break // runs until number = 0
      }
      switch(true){ // finds the best case for the current number
        case number > 0 && number <= 3:
          number-=1; // removes the amount off the number whilst
          romanNumeral+= "I"; // adding the amount to the string
          break
        case number == 4:
          number-=4;
          romanNumeral+= "IV";
          break
        case number > 4 && number <= 8:
          number-=5;
          romanNumeral+= "V";
          break
        case number == 9:
          number-=9;
          romanNumeral+= "IX";
          break
        case number > 9 && number <= 39:
          number-=10;
          romanNumeral+= "X";
          break
        case number > 39 && number <= 49:
          number-=40;
          romanNumeral+= "XL";
          break
        case number > 49 && number <= 89:
          number-=50;
          romanNumeral+= "L";
          break
        case number > 89 && number <= 99:
          number-=90;
          romanNumeral+= "XC";
          break
        case number > 99 && number <= 399:
          number-=100;
          romanNumeral+= "C";
          break
        case number > 399 && number <= 499:
          number-=400;
          romanNumeral+= "CD";
          break
        case number > 499 && number <= 899:
          number-=500;
          romanNumeral+= "D";
          break
        case number > 899 && number <= 999:
          number-=900;
          romanNumeral+= "CM";
          break
        case number > 999:
          number-=1000;
          romanNumeral+= "M";
          break
        }
      }
    return romanNumeral; // display the deciphered Roman Numeral
  
  }
  
  convertToRoman(68);