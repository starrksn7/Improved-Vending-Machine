import useTransactionsContext from '../hooks/use-transaction-context';

function ItemShow({item}){

    const { makeSale } = useTransactionsContext;
    
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