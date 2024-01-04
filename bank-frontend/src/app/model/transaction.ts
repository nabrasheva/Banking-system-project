export interface Transaction{
  sentAmount:number,
  receiverIban:string,
  reason:string,
  creditPayment?:boolean,
  issueDate?: number
}
