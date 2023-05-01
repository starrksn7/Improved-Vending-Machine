import BalanceShow from "./BalanceShow";
import useMachineContext from "../hooks/use-machine-context";

function BalanceList(){
    const {balance} = useMachineContext();

    const renderedBalance = balance.map((amount) => {
        return <BalanceShow key={amount.total} amount={amount} />
    });

    return <div className='balance'>
        {renderedBalance}
    </div>;
}

export default BalanceList;