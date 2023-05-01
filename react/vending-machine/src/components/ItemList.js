import ItemShow from "./ItemShow";
import useMachineContext from "../hooks/use-machine-context";

function ItemList(){
    const {items} = useMachineContext();

    const renderedItems = items.map((item) => {
        console.log(items)
        return <ItemShow key={item.location} item={item} />
    });

    return <div className='item-list'>
        {renderedItems}
    </div>;
}

export default ItemList;