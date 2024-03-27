import { AbstractControl, ValidationErrors, ValidatorFn } from "@angular/forms";

export function passwordValidator(regexPattern: RegExp): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
        const password = control.value as string;

        if (!password) {
            return null;
        }

        if (regexPattern.test(password)) {
            return null;
        } else {
            return {invalidPassword: true}
        }
    }
}