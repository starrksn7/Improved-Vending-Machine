import { useContext } from "react";
import MachineContext from "../context/machine";

function useMachineContext(){
    return useContext(MachineContext);
}

export default useMachineContext;