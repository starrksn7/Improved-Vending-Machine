import ItemShow from "./ItemShow";
import useMachineContext from "../hooks/use-machine-context";

function ItemList(){
    const {items} = useMachineContext();

    const renderedItems = items.map((item) => {
       
        return <ItemShow key={item.location} item={item} />
    });

    return (
    <div>
        <div className='info-list'>
            <div>Item Number</div>
            <div>Name</div>
            <div>Item Type</div>
            <div>Cost</div>
            <div>Current Stock</div>
            <div>Click to Buy</div>
        </div>
        <div>
           {renderedItems} 
        </div>   
    </div>
    )
}

export default ItemList;