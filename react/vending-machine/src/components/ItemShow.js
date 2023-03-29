import useMachineContext from '../hooks/use-machine-context';

function ItemShow({item}){

    const { makeSale } = useMachineContext;
    
    const handleClick = () => {
        makeSale(item.location);
    };
    
    return (
        <div>
            <h2>{item.location}</h2>
            <h2>{item.itemName}</h2>
            <h2>{item.itemType}</h2>
            <h2>{item.cost}</h2>
            <h2>{item.itemStock}</h2>
            <button onClick={handleClick}>Purchase</button>
        </div>
    )
}

export default ItemShow;