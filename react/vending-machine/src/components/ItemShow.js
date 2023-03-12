import { useState } from "react";
import useItemContext from "../context/items";
import useTransactionContext from "../context/transactions";

function ItemShow({item}) {

    const {fetchItems} = useItemContext();

    const {makeSale} = useTransactionContext();
    
    return (
        <div className="item-show">
            <div>{item.location}</div>
            <div>{item.itemName}</div>
            <div>{item.itemType}</div>
            <div>{item.cost}</div>
            <div>{item.itemStock}</div>
            <div>
                <button className="purchase">Purchase</button>
            </div>
        </div>
    )
}

export default ItemShow;