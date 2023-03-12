import ItemShow from "./ItemShow";
import useItemContext from "../context/items";
import useTransactionContext from "../context/transactions";d

function ItemList(){
    const {items} = useItemContext();

    const renderedItems = items.map((item) => {
        return <ItemShow key={item.location} item={item} />
    });

    return <div className="item-list">
        {renderedItems}
    </div>
}

export default ItemList