// FreeCodeCamp Javascript Algorithm Scripting Certificate projects 1-5

// 1. Palindrome Checker

function palindrome(str) {
    const removeRegex = /[\W_]/g // regex to match removable chars
    let stringOne = [...str.toLowerCase().replace(removeRegex, "").split("")]; 
    // convert to lowercase, replace all matched chars with ""
    // then split into an array of individual remaining chars 
    let stringTwo = [...stringOne].reverse();
    // copy array and reverse the order
  
    for (let letter in stringOne){
      if (stringOne[letter] != stringTwo[letter]){
        return false; // if letter does not match return false!
      } // loop through both strings comparing index elements
        // one by one
    }
  
    return true; // if all letters match return true!
  }
  
  palindrome("1 eye for of 1 eye.");

  // 2. Roman Numeral Converter

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

  // 3. Caesars Cipher

  function rot13(str) {
    let stringArray = [...str] // creates array of string
    for (let i = 0; i < stringArray.length; i++){
      if (stringArray[i].charCodeAt(0) > 64 && stringArray[i].charCodeAt(0) <= 90){ // if ascii code between 65 - 90
        stringArray[i] = stringArray[i].charCodeAt(0)+13;
        if (stringArray[i] > 90) { // 65 = (A) - 90 = (Z)
          stringArray[i] -= 26; // loops through upper letters
        }
      }else{ // if element is not a letter, just convert to ascii
        stringArray[i] = stringArray[i].charCodeAt(0);
      }
    }
    let newString = stringArray.map(asciiCode => String.fromCharCode(asciiCode)); // ascii numbers to chars
  
    return newString.join(""); // return decoded string 
  } 
  
  rot13("SERR PBQR PNZC");

  // 4. Telephone Number Validator (US)

  function telephoneCheck(str) {
    const validPhoneNoRegex = /^1 \d{3}-\d{3}-\d{4}$|^\d{9}\d$|^\d{3}-\d{3}-\d{4}$|^1 [(]\d{3}[)] \d{3}-\d{4}$|^1?[(]\d{3}[)]\d{3}-\d{4}$|^1 \d{3} \d{3} \d{4}$/;
    // Regex to match all valid US phone numbers!
    
      if (str.match(validPhoneNoRegex)){ // if a match is found return true
        return true
      }
      return false; // else if no match is found return false
    }
    
    telephoneCheck("1 555 555 5555");

  // 5. Cash Register

  function checkCashRegister(price, cash, cid) {
    let changeOwed = cash - price;
    let changeGiven = [["PENNY", 0], ["NICKEL", 0], ["DIME", 0], ["QUARTER", 0], ["ONE", 0], ["FIVE", 0], ["TEN", 0], ["TWENTY", 0], ["ONE HUNDRED", 0]]; // placeholder change provided
    let totalCID = 0;
    let currency = [ // currency values for each amount
      ["PENNY", 0.01],
      ["NICKEL", 0.05],
      ["DIME", 0.10],
      ["QUARTER", 0.25],
      ["ONE", 1],
      ["FIVE", 5],
      ["TEN", 10],
      ["TWENTY", 20],
      ["ONE HUNDRED", 100]
    ];
    for (let i = 0; i < cid.length; i++){
      totalCID += cid[i][1]; // find the total amount in CID
    }
    if (totalCID < changeOwed){ 
      // if total < owed then insufficient funds
      return {status: "INSUFFICIENT_FUNDS", change: []};
    }
    else if (totalCID == changeOwed){
      // if total equals owed then close
      return {status: "CLOSED", change: [...cid]}
    }else { // if total > owed
      for (let i = cid.length-1; i >= 0; i--){ // start from bottom
        if (cid[i][1] != 0){ // make sure drawer has enough cash
          while(changeOwed >= currency[i][1] && cid[i][1] > 0){
            // check if owed amount is greater than currency
            // and drawer still has the currency
            changeGiven[i][1] += currency[i][1]; // currency stored
            cid[i][1] -= currency[i][1]; // removed from drawer
            changeOwed -= currency[i][1]; // owed amount decreased
            changeOwed = changeOwed.toFixed(2) // 2 decimal places
          }
        }
      }
      if(changeOwed == 0){ 
        // if owed now equals 0 output given change!
        changeGiven = changeGiven.filter(array => array[1] > 0);
        return {status: "OPEN", change: changeGiven.reverse()}
      }else{ // although change in drawer was greater, the amount
      // owed can not be given due to the remaining currency
        return {status: "INSUFFICIENT_FUNDS", change: []};
      }
    }
  }
  
  console.log(checkCashRegister(3.26, 100, [["PENNY", 1.01], ["NICKEL", 2.05], ["DIME", 3.1], ["QUARTER", 4.25], ["ONE", 90], ["FIVE", 55], ["TEN", 20], ["TWENTY", 60], ["ONE HUNDRED", 100]]));