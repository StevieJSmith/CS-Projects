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