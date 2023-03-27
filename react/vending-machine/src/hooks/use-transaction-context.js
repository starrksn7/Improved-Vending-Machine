import {useContext} from 'react';
import transactionContext from "../context/transactions";

function useTransactionsContext(){
    return useContext(transactionContext);
}

export default useTransactionsContext;