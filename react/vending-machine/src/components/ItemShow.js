import useMachineContext from '../hooks/use-machine-context';

function ItemShow({item}){

    const { makeSale } = useMachineContext();
    
    const handleClick = () => {
        makeSale(item.location);
    };
    
    return (
        <div>
            <div>{item.location}</div>
            <div>{item.itemName}</div>
            <div>{item.itemType}</div>
            <div>{item.cost}</div>
            <div>{item.itemStock}</div>
            <button onClick={handleClick}>Purchase</button>
        </div>
    )
}

export default ItemShow;