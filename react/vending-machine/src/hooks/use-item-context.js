import { useContext } from "react";
import itemContext from "../context/items";

function useItemsContext(){
    return useContext(itemContext);
}

export default useItemsContext;