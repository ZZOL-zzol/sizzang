
const TextInput = (props) => {
    return(
        <input type="text" placeholder={props.placeholder} id={props.id} value={props.value} onChange={props.onChange} className="input w-full border-2 border-blue-100 focus:outline-blue-500" />
    )
};

export default TextInput;