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