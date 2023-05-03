import useMachineContext from '../hooks/use-machine-context'

function ChangeButton() {


    const {makeChange, balance} = useMachineContext();

    const handleClick = event =>{
        event.preventDefault();
        
        let quarterValue = .25;
        let dimeValue = .10;
        let nickelValue = .05;
        let pennyValue = .01;
        let numOfQuarters = 0;
        let numOfDimes = 0;
        let numOfNickels = 0;
        let numOfPennies = 0;
        let moneyToReturn = balance.total;

        makeChange();

        while (true) {
            console.log(moneyToReturn);
            if (moneyToReturn >= quarterValue) {
                moneyToReturn = Number(moneyToReturn - quarterValue).toFixed(2);
                numOfQuarters++;
            } else if (moneyToReturn >= dimeValue) {
                moneyToReturn = Number(moneyToReturn - dimeValue).toFixed(2);
                numOfDimes++;
            } else if (moneyToReturn >= nickelValue) {
                moneyToReturn = Number(moneyToReturn - nickelValue).toFixed(2);
                numOfNickels++;
            } else if (moneyToReturn >= pennyValue) {
                moneyToReturn = Number(moneyToReturn - pennyValue).toFixed(2);
                numOfPennies++;
            } else {
                break;
            }
        }
        
        alert(`Your change is ${numOfQuarters} quarters, ${numOfDimes} dimes, ${numOfNickels} nickels and ${numOfPennies} pennies`);
    };

    return (    
            <button onClick={handleClick}>I'm finished, give me my change</button>
            
            
   
    )
}

export default ChangeButton;