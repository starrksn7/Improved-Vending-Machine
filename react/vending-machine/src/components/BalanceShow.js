function BalanceShow({balance}){
    return <div>{Number(balance.total).toFixed(2)}</div>
}

export default BalanceShow;