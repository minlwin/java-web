document.addEventListener('DOMContentLoaded', () => {
	setSummary('count', 'allCount')
	setSummary('total', 'allTotal')
	registerSummaryCalculation()
	registerTotalCalculation()
})

function registerTotalCalculation() {
	document.querySelectorAll('input[name="price"]').forEach(element => element.addEventListener('change', setTotalForRow))
	document.querySelectorAll('input[name="count"]').forEach(element => element.addEventListener('change', setTotalForRow))
}

function setTotalForRow() {
	var row = this.parentElement.parentElement
	
	var price = getInput(row, 'price').value
	var count = getInput(row, 'count').value
	
	if(count == 0) {
		row.parentElement.removeChild(row)	
	} else {
		getInput(row, 'total').value = price * count	
	}
	
	setSummary('total', 'allTotal')
}

function getInput(row, name) {
	return row.querySelector(`input[name="${name}"]`)
}

function registerSummaryCalculation() {
	document.querySelectorAll('input[name="count"]').forEach(element => {
		element.addEventListener('change', () => setSummary('count', 'allCount'))
	})

	document.querySelectorAll('input[name="total"]').forEach(element => {
		element.addEventListener('change', () => setSummary('total', 'allTotal'))
	})
}

function setSummary(name, target) {
	var countInputs = document.querySelectorAll(`input[name="${name}"]`)
	var sumOfCount = Array.prototype.map.call(countInputs, input => input.value).reduce((a, b) => Number.parseInt(a) + Number.parseInt(b))
	document.querySelector(`input[name="${target}"]`).value = sumOfCount
}


function addDetails() {
	
	var container = document.getElementById('detailsWrapper')
	var row = document.createElement("tr")
	container.appendChild(row)
	
	// Item
	row.appendChild(getTextCell('item', 'Enter Item'))
	
	
	// remark
	row.appendChild(getTextCell('remark', 'Enter Remark'))
	
	// Unit Price
	row.appendChild(getNumberCell('price', 0))
	
	// Quentity 
	row.appendChild(getNumberCell('count', 1))
	
	// Total
	row.appendChild(getNumberCell('total', 0, 'readonly'))
	
	// Calculation Start	
	setSummary('count', 'allCount')
	setSummary('total', 'allTotal')
	
	// Set Registration for new Row
	registerSummaryCalculation()
	registerTotalCalculation()
}

function getNumberCell(name, value, readonly) {
	
	var input = document.createElement("input")
	
	input.setAttribute('type', "number")
	input.setAttribute('name', name)	
	input.setAttribute('value', value)
	
	if(readonly) {
		input.setAttribute('readonly', 'readonly')
	}

	var cell = document.createElement("td")
	cell.appendChild(input)
	return cell	
}

function getTextCell(name, placeholder) {
	
	var input = document.createElement("input")
	
	input.setAttribute('type', "text")
	input.setAttribute('name', name)
	input.setAttribute('placeholder', placeholder)

	var cell = document.createElement("td")
	cell.appendChild(input)
	return cell	
}