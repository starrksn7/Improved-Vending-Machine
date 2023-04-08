import useMachineContext from '../hooks/use-machine-context';

function ItemShow({item}){

    const { makeSale } = useMachineContext();
    
    const handleClick = location => event => {
        // event.preventDefault();
        makeSale(location);
    };
    
    return (
        <div>
            <div>{item.location}</div>
            <div>{item.itemName}</div>
            <div>{item.itemType}</div>
            <div>{item.cost}</div>
            <div>{item.itemStock}</div>
            <button onClick={handleClick(item.location)}>Purchase</button>
        </div>
    )
}

export default ItemShow;