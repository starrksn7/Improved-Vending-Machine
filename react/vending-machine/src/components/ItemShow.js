import useMachineContext from '../hooks/use-machine-context';
import BalanceList from './BalanceList';

function ItemShow({item}){

    const { makeSale, balance} = useMachineContext();


    const handleClick = location => event => {
        event.preventDefault();
        makeSale(item.location);
        
        console.log(balance)
        if(Number(balance).toFixed(2) >= Number(item.cost).toFixed(2)){
            if(item.itemStock > 0) {
                        makeSale(location);
                    } else {
                        alert(`That item is currently out of stock`)
                    }
        } else {
            alert(`You do not have enough deposited for that item. Please deposit ${Number(balance).toFixed(2) - Number(item.cost).toFixed(2)}`)
        }
    };
    
    return (
        <div>
            <div>{item.location}</div>
            <div>{item.itemName}</div>
            <div>{item.itemType}</div>
            <div>{Number(item.cost).toFixed(2)}</div>
            <div>{item.itemStock}</div>
            <button onClick={handleClick(item.location)}>Purchase</button>
        </div>
    )
}

export default ItemShow;