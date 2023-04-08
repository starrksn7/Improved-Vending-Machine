import useMachineContext from '../hooks/use-machine-context'

function ChangeButton() {


    const {makeChange} = useMachineContext();

    const handleClick = event =>{
        event.preventDefault();
        makeChange();
    };

    return (    
            <button onClick={handleClick}>I'm finished, give me my change</button>
            
            
   
    )
}

export default ChangeButton;