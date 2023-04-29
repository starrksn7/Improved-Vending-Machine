import BalanceShow from "./BalanceShow";
import useMachineContext from "../hooks/use-machine-context";

function BalanceList(){
    const {fetchedBalance} = useMachineContext();

    const renderedBalance = fetchedBalance.map((balance) => {
        return <BalanceShow key={balance.total} balance={balance} />
    });

    return <div className='balance'>
        {renderedBalance}
    </div>;
}

export default BalanceList;