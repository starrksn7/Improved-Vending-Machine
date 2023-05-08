import ItemShow from "./ItemShow";
import useMachineContext from "../hooks/use-machine-context";

function ItemList(){
    const {items} = useMachineContext();

    const renderedItems = items.map((item) => {
       
        return <ItemShow key={item.location} item={item} />
    });

    return (
    <div>
        <div className="item-box">
           {renderedItems} 
        </div>   
    </div>
    )
}

export default ItemList;