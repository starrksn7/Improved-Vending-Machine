import { useContext } from "react";
import machineContext from "../context/machine";

function useMachineContext(){
    return useContext(machineContext);
}

export default useMachineContext;