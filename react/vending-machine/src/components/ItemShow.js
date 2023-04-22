import useMachineContext from '../hooks/use-machine-context';


function ItemShow({item}){

    const { makeSale } = useMachineContext();
    const {balance} = useMachineContext();
    
    const handleClick = location => event => {
        // event.preventDefault();

        console.log(`Your balance is and the cost is ${Number(item.cost)}`)
        
        if(balance >= Number(item.cost)){
            if(item.itemStock > 0) {
                        makeSale(location);
                    } else {
                        alert(`That item is currently out of stock`)
                    }
        } else {
            alert(`You do not have enough deposited for that item. Please deposit ${balance - Number(item.cost)}`)
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