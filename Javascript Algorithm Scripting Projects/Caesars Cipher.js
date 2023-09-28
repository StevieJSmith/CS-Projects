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