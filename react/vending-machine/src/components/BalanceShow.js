function BalanceShow({amount}){
    return <div>{Number(amount.total).toFixed(2)}</div>
}

export default BalanceShow;