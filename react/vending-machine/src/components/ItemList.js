import ItemShow from "./ItemShow";
import useItemsContext from "../hooks/use-item-context";

function ItemList(){
    const {items} = useItemsContext();

    const renderedItems = items.map((item) => {
        return <ItemShow key={item.location} item={item} />
    });

    return <div className='item-list'>
        {renderedItems}
    </div>;
}

export default ItemList;