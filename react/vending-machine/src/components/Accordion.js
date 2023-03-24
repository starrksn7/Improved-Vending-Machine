import { useState } from "react";

function Accordion({sections}){
    const [expandedIndex, setExpandedIndex] = useState(-1);

    const handleClick = (nextIndex) => {
        
        setExpandedIndex((currentExpandedIndex) => {
            if(currentExpandedIndex === nextIndex){
                return -1
            } else {
                return nextIndex
            }
        })
    };

    const renderedSections = sections.map((section, index) => {
        const isExpanded = index === expandedIndex;

        return (
            <div key={section.id}>
                <div onClick={() => handleClick(index)}>
                    {section.label}
                </div>
                {isExpanded && <div>{section.content}</div>}
            </div>
        );
    });

    return <div>{renderedSections}</div>
}

export default Accordion;