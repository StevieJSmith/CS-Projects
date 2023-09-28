function telephoneCheck(str) {
    const validPhoneNoRegex = /^1 \d{3}-\d{3}-\d{4}$|^\d{9}\d$|^\d{3}-\d{3}-\d{4}$|^1 [(]\d{3}[)] \d{3}-\d{4}$|^1?[(]\d{3}[)]\d{3}-\d{4}$|^1 \d{3} \d{3} \d{4}$/;
    // Regex to match all valid US phone numbers!
    
      if (str.match(validPhoneNoRegex)){ // if a match is found return true
        return true
      }
      return false; // else if no match is found return false
    }
    
    telephoneCheck("1 555 555 5555");