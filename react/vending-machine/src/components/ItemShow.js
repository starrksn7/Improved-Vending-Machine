import useMachineContext from '../hooks/use-machine-context';


function ItemShow({item}){

    const { makeSale, balance} = useMachineContext();


    const handleClick = location => event => {
        event.preventDefault();
        console.log(balance.total);
       
        
        if(balance.total >= item.cost){
            if(item.itemStock > 0) {
                        makeSale(location);
                        if(item.location.includes('A')){
                            alert(`Crunch! Crunch! Yum! Enjoy your chips!  Your remaining balance is ${Number(balance.total - item.cost).toFixed(2)}`)
                        } else if(location.includes('B')){
                            alert(`Munch! Munch! Yum! Enjoy your candy!  Your remaining balance is ${Number(balance.total - item.cost).toFixed(2)}`)
                        } else if(location.includes('C')){
                            alert(`Glug! Glug! Yum! Enjoy your drink!  Your remaining balance is ${Number(balance.total - item.cost).toFixed(2)}`)
                        } else {
                            alert(`Chew! Chew! Yum! Enjoy your gum!  Your remaining balance is ${Number(balance.total - item.cost).toFixed(2)}`)
                        }
                    } else {
                        alert(`That item is currently out of stock`)
                    }
        } else {
            alert(`You do not have enough deposited for that item. Please deposit ${Number(Number(item.cost).toFixed(2) - Number(balance.total).toFixed(2)).toFixed(2)}`)
        }
    };
    
    return (
        <div className="item-list">
            <div>{item.itemName}</div>
            <div>${Number(item.cost).toFixed(2)}</div>
            <div>In Stock: {item.itemStock}</div>
            <button onClick={handleClick(item.location)}>Purchase</button>
        </div>
    )
}

export default ItemShow;